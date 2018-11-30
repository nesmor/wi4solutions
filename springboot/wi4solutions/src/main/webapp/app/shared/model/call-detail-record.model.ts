import { Moment } from 'moment';

export interface ICallDetailRecord {
    id?: number;
    calldate?: Moment;
    clid?: string;
    src?: string;
    dst?: string;
    dcontext?: string;
    channel?: string;
    dstchannel?: string;
    lastapp?: string;
    lastdata?: string;
    duration?: number;
    billsec?: number;
    disposition?: string;
    amaflags?: number;
    accountcode?: string;
    uniqueid?: string;
    userfield?: string;
}

export class CallDetailRecord implements ICallDetailRecord {
    constructor(
        public id?: number,
        public calldate?: Moment,
        public clid?: string,
        public src?: string,
        public dst?: string,
        public dcontext?: string,
        public channel?: string,
        public dstchannel?: string,
        public lastapp?: string,
        public lastdata?: string,
        public duration?: number,
        public billsec?: number,
        public disposition?: string,
        public amaflags?: number,
        public accountcode?: string,
        public uniqueid?: string,
        public userfield?: string
    ) {}
}
