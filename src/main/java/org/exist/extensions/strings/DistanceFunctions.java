package org.exist.extensions.strings;

import org.apache.commons.text.similarity.CosineDistance;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.exist.xquery.BasicFunction;
import org.exist.xquery.FunctionSignature;
import org.exist.xquery.XPathException;
import org.exist.xquery.XQueryContext;
import org.exist.xquery.value.DoubleValue;
import org.exist.xquery.value.IntegerValue;
import org.exist.xquery.value.Sequence;
import org.exist.xquery.value.Type;

import static org.exist.extensions.strings.DistanceModule.functionSignature;
import static org.exist.xquery.FunctionDSL.param;
import static org.exist.xquery.FunctionDSL.returns;

/**
 * Some very simple XQuery example functions implemented
 * in Java.
 */
public class DistanceFunctions extends BasicFunction {

    private static final String HAMMING = "hamming";
    static final FunctionSignature FS_HAMMING = functionSignature(
            HAMMING,
            "Find the Hamming Distance between two strings with the same length.",
            returns(Type.INTEGER),
            param("left", Type.STRING, "Left"),
            param("right", Type.STRING, "Right")
    );

    private static final String LEVENSHTEIN = "levenshtein";
    static final FunctionSignature FS_LEVENSHTEIN = functionSignature(
            LEVENSHTEIN,
            "Find the Levenshtein distance between two strings.",
            returns(Type.INTEGER),
            param("left", Type.STRING, "Left"),
            param("right", Type.STRING, "Right")
    );

    private static final String COSINE = "cosine";
    static final FunctionSignature FS_COSINE = functionSignature(
            COSINE,
            "Measures the cosine distance between two strings.",
            returns(Type.DOUBLE),
            param("left", Type.STRING, "Left"),
            param("right", Type.STRING, "Right")
    );

    public DistanceFunctions(final XQueryContext context, final FunctionSignature signature) {
        super(context, signature);
    }

    @Override
    public Sequence eval(final Sequence[] args, final Sequence contextSequence) throws XPathException {

        final String left = args[0].getStringValue();
        final String right = args[1].getStringValue();

        switch (getName().getLocalPart()) {
            case LEVENSHTEIN:
                final LevenshteinDistance ld = new LevenshteinDistance();
                return new IntegerValue(ld.apply(left, right));

            case HAMMING:
                final HammingDistance hd = new HammingDistance();
                return new IntegerValue(hd.apply(left, right));

            case COSINE:
                final CosineDistance cd = new CosineDistance();
                return new DoubleValue(cd.apply(left, right));

            default:
                throw new XPathException(this, "No function: " + getName() + "#" + getSignature().getArgumentCount());
        }
    }

}
