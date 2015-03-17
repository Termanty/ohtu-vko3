
package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface KirjanpitoApi {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
