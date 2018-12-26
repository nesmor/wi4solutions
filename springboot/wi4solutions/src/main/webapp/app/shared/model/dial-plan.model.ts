import { IGateway } from 'app/shared/model//gateway.model';

export interface IDialPlan {
    id?: number;
    name?: string;
    description?: string;
    prefix?: string;
    digitCut?: string;
    preceding?: string;
    priority?: number;
    limit?: number;
    gateway?: IGateway;
    status?: number;
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
        public limit?: number,
        public gateway?: IGateway,
        public status?: number
    ) {}
}
