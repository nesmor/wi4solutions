export interface IMessage {
    code?: number;
    message?: string;
}

export class Message implements IMessage {
    constructor(public code?: number, public message?: string) {}
}
