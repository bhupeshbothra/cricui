import { Bowling } from './Bowling';
import { Batting } from './Batting';
export class DetailsData {
    public batting: Batting;
    public bowling: Bowling;

    constructor() {
        this.batting = undefined;
        this.bowling = undefined;
    }
}