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
import io.ballerina.compiler.api.symbols.FieldSymbol;
import io.ballerina.compiler.api.symbols.MethodSymbol;
import io.ballerina.compiler.api.symbols.ObjectTypeSymbol;
import io.ballerina.compiler.api.symbols.Qualifier;
import io.ballerina.compiler.api.symbols.SymbolKind;
import io.ballerina.compiler.api.symbols.TypeDescKind;
import org.ballerinalang.model.elements.PackageID;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BClassSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.BSymbol;
import org.wso2.ballerinalang.compiler.semantics.model.symbols.Symbols;
import org.wso2.ballerinalang.compiler.util.CompilerContext;
import org.wso2.ballerinalang.util.Flags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a Class Symbol.
 *
 * @since 2.0.0
 */
public class BallerinaClassSymbol extends BallerinaSymbol implements ClassSymbol {

    private final ObjectTypeSymbol typeDescriptor;
    private final Set<Qualifier> qualifiers;
    private final boolean deprecated;
    private final BClassSymbol internalSymbol;
    private final CompilerContext context;
    private MethodSymbol initMethod;

    protected BallerinaClassSymbol(CompilerContext context, String name, PackageID moduleID, Set<Qualifier> qualifiers,
                                   ObjectTypeSymbol typeDescriptor, BClassSymbol classSymbol) {
        super(name, moduleID, SymbolKind.CLASS, classSymbol);
        this.qualifiers = Collections.unmodifiableSet(qualifiers);
        this.typeDescriptor = typeDescriptor;
        this.deprecated = Symbols.isFlagOn(classSymbol.flags, Flags.DEPRECATED);
        this.internalSymbol = classSymbol;
        this.context = context;
    }

    @Override
    public List<FieldSymbol> fieldDescriptors() {
        return this.typeDescriptor.fieldDescriptors();
    }

    @Override
    public List<MethodSymbol> methods() {
        return this.typeDescriptor.methods();
    }

    @Override
    public Optional<MethodSymbol> initMethod() {
        if (this.initMethod == null && this.internalSymbol.initializerFunc != null) {
            SymbolFactory symbolFactory = SymbolFactory.getInstance(this.context);
            this.initMethod = symbolFactory.createMethodSymbol(internalSymbol.initializerFunc.symbol,
                                                               internalSymbol.initializerFunc.funcName.value);

        }

        return Optional.ofNullable(this.initMethod);
    }

    @Override
    public List<TypeQualifier> typeQualifiers() {
        return this.typeDescriptor.typeQualifiers();
    }

    @Override
    public TypeDescKind typeKind() {
        return TypeDescKind.OBJECT;
    }

    @Override
    public String signature() {
        if (this.name().startsWith("$anonType$")) {
            return typeDescriptor.signature();
        }
        return this.name();
    }

    @Override
    public List<MethodSymbol> builtinMethods() {
        return new ArrayList<>();
    }

    @Override
    public boolean deprecated() {
        return this.deprecated;
    }

    @Override
    public Set<Qualifier> qualifiers() {
        return this.qualifiers;
    }

    /**
     * A builder for class symbols.
     *
     * @since 2.0.0
     */
    public static class ClassSymbolBuilder extends SymbolBuilder<BallerinaClassSymbol.ClassSymbolBuilder> {

        protected Set<Qualifier> qualifiers = new HashSet<>();
        protected ObjectTypeSymbol typeDescriptor;
        protected CompilerContext context;

        public ClassSymbolBuilder(CompilerContext context, String name, PackageID moduleID, BSymbol symbol) {
            super(name, moduleID, SymbolKind.CLASS, symbol);
            this.context = context;
        }

        public BallerinaClassSymbol.ClassSymbolBuilder withTypeDescriptor(ObjectTypeSymbol typeDescriptor) {
            this.typeDescriptor = typeDescriptor;
            return this;
        }

        public BallerinaClassSymbol.ClassSymbolBuilder withQualifier(Qualifier qualifier) {
            this.qualifiers.add(qualifier);
            return this;
        }

        @Override
        public BallerinaClassSymbol build() {
            return new BallerinaClassSymbol(this.context, this.name, this.moduleID, this.qualifiers,
                                            this.typeDescriptor, (BClassSymbol) this.bSymbol);
        }
    }
}
