export interface IActiveCall {
    id?: number;
    number?: string;
    originator?: string;
    ani?: string;
    dni?: string;
    gateway?: string;
    duration?: string;
    status?: string;
}

export class ActiveCall implements IActiveCall {
    constructor(
        public id?: number,
        public number?: string,
        public originator?: string,
        public ani?: string,
        public dni?: string,
        public gateway?: string,
        public duration?: string,
        public status?: string
    ) {}
}
