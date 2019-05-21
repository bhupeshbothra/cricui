import { JsonProperty } from "json2typescript";

export class FirstClass {
    @JsonProperty('10')
    bo10: string;
    @JsonProperty('5w')
    bo5w: string;
    @JsonProperty('4w')
    bo4w: string;
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