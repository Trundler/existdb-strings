package org.exist.extensions.strings;

import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Nysiis;
import org.apache.commons.codec.language.Soundex;
import org.exist.xquery.BasicFunction;
import org.exist.xquery.FunctionSignature;
import org.exist.xquery.XPathException;
import org.exist.xquery.XQueryContext;
import org.exist.xquery.value.Sequence;
import org.exist.xquery.value.StringValue;
import org.exist.xquery.value.Type;

import static org.exist.extensions.strings.CodecModule.functionSignature;
import static org.exist.xquery.FunctionDSL.param;
import static org.exist.xquery.FunctionDSL.returns;

/**
 * Some very simple XQuery example functions implemented
 * in Java.
 */
public class CodecFunctions extends BasicFunction {

    private static final String SOUNDEX = "soundex";
    static final FunctionSignature FS_SOUNDEX = functionSignature(
            SOUNDEX,
            "Encodes a String using the soundex algorithm.",
            returns(Type.STRING),
            param("str", Type.STRING, "Input string")
    );

    private static final String NYSIIS = "nysiis";
    static final FunctionSignature FS_NYSIIS = functionSignature(
            NYSIIS,
            "Encodes a String using the Nysiis algorithm.",
            returns(Type.STRING),
            param("str", Type.STRING, "Input string")
    );

    private static final String METAPHONE = "metaphone";
    static final FunctionSignature FS_METAPHONE = functionSignature(
            METAPHONE,
            "Encodes a String using the Metaphone algorithm.",
            returns(Type.STRING),
            param("str", Type.STRING, "Input string")
    );


    private static final String COLOGNE_PHONETIC = "cologne-phonetic";
    static final FunctionSignature FS_COLOGNE_PHONETIC = functionSignature(
            COLOGNE_PHONETIC,
            "Encodes a String using the Cologne Phonetic algorithm.",
            returns(Type.STRING),
            param("str", Type.STRING, "Input string")
    );


    public CodecFunctions(final XQueryContext context, final FunctionSignature signature) {
        super(context, signature);
    }

    @Override
    public Sequence eval(final Sequence[] args, final Sequence contextSequence) throws XPathException {

        final String str = args[0].getStringValue();

        switch (getName().getLocalPart()) {
            case SOUNDEX:
                final Soundex soundex = new Soundex();
                return new StringValue(soundex.encode(str));

            case NYSIIS:
                final Nysiis nysiis = new Nysiis();
                return new StringValue(nysiis.encode(str));

            case METAPHONE:
                final Metaphone metaphone = new Metaphone();
                return new StringValue(metaphone.encode(str));

            case COLOGNE_PHONETIC:
                final ColognePhonetic colognePhonetic = new ColognePhonetic();
                return new StringValue(colognePhonetic.encode(str));

            default:
                throw new XPathException(this, String.format("No function: %s#%d", getName(), getSignature().getArgumentCount()));
        }
    }

}
