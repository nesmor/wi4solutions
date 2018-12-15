import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICallReport } from 'app/shared/model/call-report.model';

type EntityResponseType = HttpResponse<ICallReport>;
type EntityArrayResponseType = HttpResponse<ICallReport[]>;

@Injectable({ providedIn: 'root' })
export class CallReportService {
    public resourceUrl = SERVER_API_URL + 'api/call-reports';

    constructor(private http: HttpClient) {}

    create(callReport: ICallReport): Observable<EntityResponseType> {
        return this.http.post<ICallReport>(this.resourceUrl, callReport, { observe: 'response' });
    }

    update(callReport: ICallReport): Observable<EntityResponseType> {
        return this.http.put<ICallReport>(this.resourceUrl, callReport, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICallReport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICallReport[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    findByDate(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICallReport[]>(`${this.resourceUrl}/by-date`, { params: options, observe: 'response' });
        //   .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    findByHour(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICallReport[]>(`${this.resourceUrl}/by-hour`, { params: options, observe: 'response' });
        //   .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fromDate = res.body.fromDate != null ? moment(res.body.fromDate) : null;
            res.body.toDate = res.body.toDate != null ? moment(res.body.toDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((callReport: ICallReport) => {
                callReport.fromDate = callReport.fromDate != null ? moment(callReport.fromDate) : null;
                callReport.toDate = callReport.toDate != null ? moment(callReport.toDate) : null;
            });
        }
        return res;
    }
}
