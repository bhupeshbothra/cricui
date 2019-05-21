
import { JsonProperty } from "json2typescript";

export class FirstClassB {
    @JsonProperty('50')
    ba50: string;
    @JsonProperty('100')
    ba100: string;
    St: string;
    Ct: string;
    @JsonProperty('6s')
    ba6s: string;
    @JsonProperty('4s')
    ba4s: string;
    SR: string;
    BF: string;
    Ave: string;
    HS: string;
    Runs: string;
    NO: string;
    Inns: string;
    Mat: string;

    constructor() {
        this.ba50 = undefined;
        this.ba100 = undefined;
        this.ba6s = undefined;
        this.ba4s = undefined;
    }
}