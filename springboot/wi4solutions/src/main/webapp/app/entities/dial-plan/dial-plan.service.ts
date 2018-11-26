import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDialPlan } from 'app/shared/model/dial-plan.model';

type EntityResponseType = HttpResponse<IDialPlan>;
type EntityArrayResponseType = HttpResponse<IDialPlan[]>;

@Injectable({ providedIn: 'root' })
export class DialPlanService {
    public resourceUrl = SERVER_API_URL + 'api/dial-plans';

    constructor(private http: HttpClient) {}

    create(dialPlan: IDialPlan): Observable<EntityResponseType> {
        return this.http.post<IDialPlan>(this.resourceUrl, dialPlan, { observe: 'response' });
    }

    update(dialPlan: IDialPlan): Observable<EntityResponseType> {
        return this.http.put<IDialPlan>(this.resourceUrl, dialPlan, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDialPlan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDialPlan[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
