import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';

type EntityResponseType = HttpResponse<ICallDetailRecord>;
type EntityArrayResponseType = HttpResponse<ICallDetailRecord[]>;

@Injectable({ providedIn: 'root' })
export class CallDetailRecordService {
    public resourceUrl = SERVER_API_URL + 'api/call-detail-records';

    constructor(private http: HttpClient) {}

    create(callDetailRecord: ICallDetailRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(callDetailRecord);
        return this.http
            .post<ICallDetailRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(callDetailRecord: ICallDetailRecord): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(callDetailRecord);
        return this.http
            .put<ICallDetailRecord>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICallDetailRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICallDetailRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(callDetailRecord: ICallDetailRecord): ICallDetailRecord {
        const copy: ICallDetailRecord = Object.assign({}, callDetailRecord, {
            calldate: callDetailRecord.calldate != null && callDetailRecord.calldate.isValid() ? callDetailRecord.calldate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.calldate = res.body.calldate != null ? moment(res.body.calldate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((callDetailRecord: ICallDetailRecord) => {
                callDetailRecord.calldate = callDetailRecord.calldate != null ? moment(callDetailRecord.calldate) : null;
            });
        }
        return res;
    }
}
