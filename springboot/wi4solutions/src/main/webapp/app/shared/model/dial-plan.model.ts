import { ISipPeer } from 'app/shared/model//sip-peer.model';

export interface IDialPlan {
    id?: number;
    name?: string;
    description?: string;
    prefix?: string;
    digitCut?: string;
    preceding?: string;
    priority?: number;
    gateway?: number;
    limit?: number;
    sipPeer?: ISipPeer;
}

export class DialPlan implements IDialPlan {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public prefix?: string,
        public digitCut?: string,
        public preceding?: string,
        public priority?: number,
        public gateway?: number,
        public limit?: number,
        public sipPeer?: ISipPeer
    ) {}
}
