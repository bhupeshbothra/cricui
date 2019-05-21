import { JsonProperty } from "json2typescript";

export class ODI {
    @JsonProperty('10')
    bo10: string;
    @JsonProperty('5w')
    bo5w: string;
    @JsonProperty('4w')
    SR: string;
    Econ: string;
    Ave: string;
    BBM: string;
    BBI: string;
    Wkts: string;
    Runs: string;
    Balls: string;
    Inns: string;
    Mat: string;
}