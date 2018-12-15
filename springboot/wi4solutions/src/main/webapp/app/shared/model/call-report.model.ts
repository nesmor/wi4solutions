import { Moment } from 'moment';

export interface ICallReport {
    id?: number;
    fromDate?: Moment;
    toDate?: Moment;
    failedCalls?: number;
    totalCalls?: number;
    totalDuration?: number;
    asr?: number;
    acd?: number;
    minutes?: number;
    connectedCalls?: number;
    reportType?: string;
    hour?: number;
    date?: string;
}

export class CallReport implements ICallReport {
    constructor(
        public id?: number,
        public fromDate?: Moment,
        public toDate?: Moment,
        public failedCalls?: number,
        public totalCalls?: number,
        public totalDuration?: number,
        public asr?: number,
        public acd?: number,
        public minutes?: number,
        public connectedCalls?: number,
        public reportType?: string,
        public hour?: number,
        public date?: string
    ) {}
}
