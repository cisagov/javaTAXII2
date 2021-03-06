package gov.dhs.cisa.ctm.taxii2;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    String TAXII_20 = "taxii-2.0";
    
    interface MediaTypes {
        /**
         * This media type was defined in TAXII 2.0 spec,
         * but deprecated in TAXII 2.1 spec
         */
        @Deprecated
        String OASIS_TAXII = "application/vnd.oasis.taxii+json";
        String TAXII = "application/taxii+json";
        String TAXII_20 = "application/vnd.oasis.taxii+json;version=2.0";
        String TAXII_21 = "application/taxii+json;version=2.1";
        /**
         * This media type was defined in TAXII 2.0 spec,
         * but deprecated in TAXII 2.1 spec
         */
        @Deprecated
        String TAXII20_STIX = "application/vnd.oasis.stix+json";
        String STIX = "application/stix+json";
        String STIX_20 = "application/vnd.oasis.stix+json;version=2.0";
        String STIX_21 = "application/stix+json;version=2.1"; //This is how the TAXII 2.1 spec has the header in examples. Need to reference with STIX 2.1 spec

        List<String> ALL_TAXII = Arrays.asList(TAXII_21, TAXII, TAXII_20);
    }
}
