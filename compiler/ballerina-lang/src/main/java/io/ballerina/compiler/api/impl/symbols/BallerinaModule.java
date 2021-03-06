/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerina.compiler.api.impl.symbols;

import io.ballerina.compiler.api.impl.SymbolFactory;
import io.ballerina.compiler.api.symbols.ClassSymbol;
import io.ballerina.compiler.api.symbols.ConstantSymbol;
import io.ballerina.compiler.api.symbols.FunctionSymbol;
import io.ballerina.compiler.api.symbols.ModuleSymbol;
import io.ballerina.compiler.api.symbols.ObjectTypeSymbol;
import io.ballerina.compiler.api.symbols.ServiceSymbol;
import io.ballerina.compiler.api.symbols.Symbol;
import io.ballerina.compiler.api.symbols.SymbolKind;
import io.ballerina.compiler.api.symbols.TypeDefinitionSymbol;
import org.ballerinalang.model.elements.PackageID;
import org.wso2.ballerinalang.compiler.semantics.model.Scope.ScopeEntry;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BClassSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BConstantSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BInvokableSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BPackageSymbol;
import org.wso2.ballerinalang.compiler.util.CompilerContext;
import org.wso2.ballerinalang.compiler.util.Name;
import org.wso2.ballerinalang.util.Flags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.ballerinalang.model.symbols.SymbolOrigin.COMPILED_SOURCE;
import static org.wso2.ballerinalang.compiler.semantics.model.symbols.Symbols.isFlagOn;

/**
 * Represents a ballerina module.
 *
 * @since 2.0.0
 */
public class BallerinaModule extends BallerinaSymbol implements ModuleSymbol {

    private final CompilerContext context;
    private BPackageSymbol packageSymbol;
    private List<TypeDefinitionSymbol> typeDefs;
    private List<ClassSymbol> classes;
    private List<FunctionSymbol> functions;
    private List<ConstantSymbol> constants;
    private List<ObjectTypeSymbol> listeners;
    private List<Symbol> allSymbols;

    protected BallerinaModule(CompilerContext context, String name, PackageID moduleID, BPackageSymbol packageSymbol) {
        super(name, moduleID, SymbolKind.MODULE, packageSymbol);
        this.context = context;
        this.packageSymbol = packageSymbol;
    }

    /**
     * Get the public functions defined within the module.
     *
     * @return {@link List} of type definitions
     */
    @Override
    public List<FunctionSymbol> functions() {
        if (this.functions != null) {
            return functions;
        }

        SymbolFactory symbolFactory = SymbolFactory.getInstance(this.context);
        List<FunctionSymbol> functions = new ArrayList<>();

        for (Map.Entry<Name, ScopeEntry> entry : this.packageSymbol.scope.entries.entrySet()) {
            ScopeEntry scopeEntry = entry.getValue();

            if (scopeEntry.symbol != null
                    && scopeEntry.symbol.kind == org.ballerinalang.model.symbols.SymbolKind.FUNCTION
                    && isFlagOn(scopeEntry.symbol.flags, Flags.PUBLIC)
                    && scopeEntry.symbol.origin == COMPILED_SOURCE) {
                String funcName = scopeEntry.symbol.getName().getValue();
                functions.add(symbolFactory.createFunctionSymbol((BInvokableSymbol) scopeEntry.symbol, funcName));
            }
        }

        this.functions = Collections.unmodifiableList(functions);
        return this.functions;
    }

    /**
     * Get the public type definitions defined within the module.
     *
     * @return {@link List} of type definitions
     */
    @Override
    public List<TypeDefinitionSymbol> typeDefinitions() {
        if (this.typeDefs == null) {
            this.typeDefs = this.allSymbols().stream()
                    .filter(symbol -> symbol.kind() == SymbolKind.TYPE)
                    .map(symbol -> (TypeDefinitionSymbol) symbol)
                    .collect(Collectors.toUnmodifiableList());
        }

        return this.typeDefs;
    }

    /**
     * Get the public class definitions defined within the module.
     *
     * @return {@link List} of class definitions
     */
    @Override
    public List<ClassSymbol> classes() {
        if (this.classes != null) {
            return this.classes;
        }

        SymbolFactory symbolFactory = SymbolFactory.getInstance(this.context);
        List<ClassSymbol> classes = new ArrayList<>();

        for (Map.Entry<Name, ScopeEntry> entry : this.packageSymbol.scope.entries.entrySet()) {
            ScopeEntry scopeEntry = entry.getValue();
            if (scopeEntry.symbol instanceof BClassSymbol &&
                    (scopeEntry.symbol.flags & Flags.PUBLIC) == Flags.PUBLIC) {
                String constName = scopeEntry.symbol.getName().getValue();
                classes.add(symbolFactory.createClassSymbol((BClassSymbol) scopeEntry.symbol, constName));
            }
        }

        this.classes = Collections.unmodifiableList(classes);
        return this.classes;
    }

    /**
     * Get the public constants defined within the module.
     *
     * @return {@link List} of type definitions
     */
    @Override
    public List<ConstantSymbol> constants() {
        if (this.constants != null) {
            return this.constants;
        }

        SymbolFactory symbolFactory = SymbolFactory.getInstance(this.context);
        List<ConstantSymbol> constants = new ArrayList<>();

        for (Map.Entry<Name, ScopeEntry> entry : this.packageSymbol.scope.entries.entrySet()) {
            ScopeEntry scopeEntry = entry.getValue();

            if (scopeEntry.symbol instanceof BConstantSymbol &&
                    (scopeEntry.symbol.flags & Flags.PUBLIC) == Flags.PUBLIC) {
                String constName = scopeEntry.symbol.getName().getValue();
                constants.add(symbolFactory.createConstantSymbol((BConstantSymbol) scopeEntry.symbol, constName));
            }
        }

        this.constants = Collections.unmodifiableList(constants);
        return this.constants;
    }

    /**
     * Get the listeners in the Module.
     *
     * @return {@link List} of listeners
     */
    @Override
    public List<ObjectTypeSymbol> listeners() {
        if (this.listeners != null) {
            return listeners;
        }

        // TODO:
        this.listeners = new ArrayList<>();
        return this.listeners;
    }

    @Override
    public List<ServiceSymbol> services() {
        return new ArrayList<>();
    }

    /**
     * Get all public the symbols within the module.
     *
     * @return {@link List} of type definitions
     */
    @Override
    public List<Symbol> allSymbols() {
        if (this.allSymbols == null) {
            SymbolFactory symbolFactory = SymbolFactory.getInstance(this.context);
            List<Symbol> symbols = new ArrayList<>();

            for (Map.Entry<Name, ScopeEntry> entry : this.packageSymbol.scope.entries.entrySet()) {
                ScopeEntry scopeEntry = entry.getValue();
                if (!isFlagOn(scopeEntry.symbol.flags, Flags.PUBLIC) || scopeEntry.symbol.origin != COMPILED_SOURCE) {
                    continue;
                }
                symbols.add(
                        symbolFactory.getBCompiledSymbol(scopeEntry.symbol, scopeEntry.symbol.getName().getValue()));
            }

            this.allSymbols = Collections.unmodifiableList(symbols);
        }

        return this.allSymbols;
    }

    /**
     * Represents Ballerina Module Symbol Builder.
     *
     * @since 2.0.0
     */
    public static class ModuleSymbolBuilder extends SymbolBuilder<ModuleSymbolBuilder> {

        private final CompilerContext context;

        public ModuleSymbolBuilder(CompilerContext context, String name,
                                   PackageID moduleID, BPackageSymbol packageSymbol) {
            super(name, moduleID, SymbolKind.MODULE, packageSymbol);
            this.context = context;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BallerinaModule build() {
            if (this.bSymbol == null) {
                throw new AssertionError("Package Symbol cannot be null");
            }
            return new BallerinaModule(this.context, this.name, this.moduleID, (BPackageSymbol) this.bSymbol);
        }
    }
}
