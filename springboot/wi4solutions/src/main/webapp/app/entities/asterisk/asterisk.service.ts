import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAsterisk } from 'app/shared/model/asterisk.model';

type EntityResponseType = HttpResponse<IAsterisk>;
type EntityArrayResponseType = HttpResponse<IAsterisk[]>;

@Injectable({ providedIn: 'root' })
export class AsteriskService {
    //public resourceUrl = SERVER_API_URL + 'api/asterisk';

    public resourceUrl = SERVER_API_URL + 'api/send-calls';

    constructor(private http: HttpClient) {}

    create(asterisk: IAsterisk): Observable<EntityResponseType> {
        return this.http.post<IAsterisk>(this.resourceUrl, asterisk, { observe: 'response' });
    }

    sendCall(asterisk: IAsterisk): Observable<EntityResponseType> {
        return this.http.post<IAsterisk>(this.resourceUrl, asterisk, { observe: 'response' });
    }

    update(asterisk: IAsterisk): Observable<EntityResponseType> {
        return this.http.put<IAsterisk>(this.resourceUrl, asterisk, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAsterisk>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAsterisk[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
