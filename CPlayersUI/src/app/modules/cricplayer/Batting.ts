import { FirstClassB } from './FirstClassB';
import { ODIB } from './ODIB';
import { ListAB } from './ListAB';
import { TestB } from './TestB';
import { T20B } from './T20B';
export class Batting {
    public firstClass: FirstClassB;
    public ODIs: ODIB;
    public listA: ListAB;
    public T20Is: T20B;
    public tests: TestB;

    constructor() {
        this.ODIs = undefined;
        this.firstClass = undefined;
        this.listA = undefined;
        this.T20Is = undefined;
        this.tests = undefined;
    }

}