import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActiveCall } from 'app/shared/model/active-call.model';

type EntityResponseType = HttpResponse<IActiveCall>;
type EntityArrayResponseType = HttpResponse<IActiveCall[]>;

@Injectable({ providedIn: 'root' })
export class ActiveCallService {
    public resourceUrl = SERVER_API_URL + 'api/active-calls';

    constructor(private http: HttpClient) {}

    create(activeCall: IActiveCall): Observable<EntityResponseType> {
        return this.http.post<IActiveCall>(this.resourceUrl, activeCall, { observe: 'response' });
    }

    update(activeCall: IActiveCall): Observable<EntityResponseType> {
        return this.http.put<IActiveCall>(this.resourceUrl, activeCall, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IActiveCall>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IActiveCall[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
