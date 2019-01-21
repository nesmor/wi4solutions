export interface IActiveCalls {
    calls?: string;
}

export class ActiveCalls implements IActiveCalls {
    constructor(public calls?: string) {}
}
