package org.exist.extensions.strings;

import org.exist.dom.QName;
import org.exist.xquery.*;
import org.exist.xquery.value.FunctionParameterSequenceType;
import org.exist.xquery.value.FunctionReturnSequenceType;

import java.util.List;
import java.util.Map;

import static org.exist.xquery.FunctionDSL.functionDefs;

/**
 * A very simple example XQuery Library Module implemented
 * in Java.
 */
public class CodecModule extends AbstractInternalModule {

    public static final String NAMESPACE_URI = "http://exist-db.org/extensions/strings/codec";
    public static final String PREFIX = "codec";
    public static final String RELEASED_IN_VERSION = "eXist-3.6.0";

    // register the functions of the module
    public static final FunctionDef[] functions = functionDefs(
            functionDefs(CodecFunctions.class,
                    CodecFunctions.FS_SOUNDEX,
                    CodecFunctions.FS_NYSIIS,
                    CodecFunctions.FS_METAPHONE,
                    CodecFunctions.FS_DOUBLEMETAPHONE,
                    CodecFunctions.FS_COLOGNE_PHONETIC,
                    CodecFunctions.FS_CAVERPHONE_1,
                    CodecFunctions.FS_CAVERPHONE_2
            )
    );

    public CodecModule(final Map<String, List<? extends Object>> parameters) {
        super(functions, parameters);
    }

    static FunctionSignature functionSignature(final String name, final String description,
                                               final FunctionReturnSequenceType returnType, final FunctionParameterSequenceType... paramTypes) {
        return FunctionDSL.functionSignature(new QName(name, NAMESPACE_URI), description, returnType, paramTypes);
    }

    static FunctionSignature[] functionSignatures(final String name, final String description,
                                                  final FunctionReturnSequenceType returnType, final FunctionParameterSequenceType[][] variableParamTypes) {
        return FunctionDSL.functionSignatures(new QName(name, NAMESPACE_URI), description, returnType, variableParamTypes);
    }

    @Override
    public String getNamespaceURI() {
        return NAMESPACE_URI;
    }

    @Override
    public String getDefaultPrefix() {
        return PREFIX;
    }

    @Override
    public String getDescription() {
        return "Module for string functions and operations.";
    }

    @Override
    public String getReleaseVersion() {
        return RELEASED_IN_VERSION;
    }

    static class ExpathBinModuleErrorCode extends ErrorCodes.ErrorCode {
        private ExpathBinModuleErrorCode(final String code, final String description) {
            super(new QName(code, NAMESPACE_URI, PREFIX), description);
        }
    }
}
