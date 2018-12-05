import { IDialPlan } from 'app/shared/model//dial-plan.model';

export interface IGateway {
    id?: number;
    name?: string;
    host?: string;
    nat?: string;
    type?: string;
    accountcode?: string;
    amaflags?: string;
    calllimit?: number;
    callgroup?: string;
    callerid?: string;
    cancallforward?: string;
    canreinvite?: string;
    context?: string;
    defaultip?: string;
    dtmfmode?: string;
    fromuser?: string;
    fromdomain?: string;
    insecure?: string;
    language?: string;
    mailbox?: string;
    md5secret?: string;
    deny?: string;
    permit?: string;
    mask?: string;
    musiconhold?: string;
    pickupgroup?: string;
    qualify?: string;
    regexten?: string;
    restrictcid?: string;
    rtptimeout?: string;
    rtpholdtimeout?: string;
    secret?: string;
    setvar?: string;
    disallow?: string;
    allow?: string;
    fullcontact?: string;
    ipaddr?: string;
    port?: number;
    regserver?: string;
    regseconds?: number;
    lastms?: number;
    username?: string;
    defaultuser?: string;
    subscribecontext?: string;
    useragent?: string;
    status?: number;
    peerType?: string;
    dialPlan?: IDialPlan;
}

export class Gateway implements IGateway {
    constructor(
        public id?: number,
        public name?: string,
        public host?: string,
        public nat?: string,
        public type?: string,
        public accountcode?: string,
        public amaflags?: string,
        public calllimit?: number,
        public callgroup?: string,
        public callerid?: string,
        public cancallforward?: string,
        public canreinvite?: string,
        public context?: string,
        public defaultip?: string,
        public dtmfmode?: string,
        public fromuser?: string,
        public fromdomain?: string,
        public insecure?: string,
        public language?: string,
        public mailbox?: string,
        public md5secret?: string,
        public deny?: string,
        public permit?: string,
        public mask?: string,
        public musiconhold?: string,
        public pickupgroup?: string,
        public qualify?: string,
        public regexten?: string,
        public restrictcid?: string,
        public rtptimeout?: string,
        public rtpholdtimeout?: string,
        public secret?: string,
        public setvar?: string,
        public disallow?: string,
        public allow?: string,
        public fullcontact?: string,
        public ipaddr?: string,
        public port?: number,
        public regserver?: string,
        public regseconds?: number,
        public lastms?: number,
        public username?: string,
        public defaultuser?: string,
        public subscribecontext?: string,
        public useragent?: string,
        public status?: number,
        public dialPlan?: IDialPlan,
        public peerType?: string
    ) {}
}
