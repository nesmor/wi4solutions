import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISipPeer } from 'app/shared/model/sip-peer.model';

type EntityResponseType = HttpResponse<ISipPeer>;
type EntityArrayResponseType = HttpResponse<ISipPeer[]>;

@Injectable({ providedIn: 'root' })
export class SipPeerService {
    public resourceUrl = SERVER_API_URL + 'api/sip-peers';

    constructor(private http: HttpClient) {}

    create(sipPeer: ISipPeer): Observable<EntityResponseType> {
        return this.http.post<ISipPeer>(this.resourceUrl, sipPeer, { observe: 'response' });
    }

    update(sipPeer: ISipPeer): Observable<EntityResponseType> {
        return this.http.put<ISipPeer>(this.resourceUrl, sipPeer, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISipPeer>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISipPeer[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
