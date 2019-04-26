package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//Tempo breakdown
// Eccentric    lowering weight
// Stretch      bottom position
// Concentric   Raising Weight
// Contracted   Top Position

public enum Tempo {
    STRETCHCONTRACT("3-2-2-5"),
    CONSTANTTENSION("3-0-2-0"),
    CONCENTRICEMPHASIS("3-2-4-0");

    private String value;
    private String getValue() { return this.value; }
    private Tempo(String value) { this.value = value; }
}
