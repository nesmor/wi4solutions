import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CallDetailRecord } from 'app/shared/model/call-detail-record.model';
import { CallDetailRecordService } from './call-detail-record.service';
import { CallDetailRecordComponent } from './call-detail-record.component';
import { CallDetailRecordDetailComponent } from './call-detail-record-detail.component';
import { CallDetailRecordUpdateComponent } from './call-detail-record-update.component';
import { CallDetailRecordDeletePopupComponent } from './call-detail-record-delete-dialog.component';
import { ICallDetailRecord } from 'app/shared/model/call-detail-record.model';

@Injectable({ providedIn: 'root' })
export class CallDetailRecordResolve implements Resolve<ICallDetailRecord> {
    constructor(private service: CallDetailRecordService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CallDetailRecord> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CallDetailRecord>) => response.ok),
                map((callDetailRecord: HttpResponse<CallDetailRecord>) => callDetailRecord.body)
            );
        }
        return of(new CallDetailRecord());
    }
}

export const callDetailRecordRoute: Routes = [
    {
        path: 'call-detail-record',
        component: CallDetailRecordComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callDetailRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-detail-record/:id/view',
        component: CallDetailRecordDetailComponent,
        resolve: {
            callDetailRecord: CallDetailRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callDetailRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-detail-record/new',
        component: CallDetailRecordUpdateComponent,
        resolve: {
            callDetailRecord: CallDetailRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callDetailRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-detail-record/:id/edit',
        component: CallDetailRecordUpdateComponent,
        resolve: {
            callDetailRecord: CallDetailRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callDetailRecord.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const callDetailRecordPopupRoute: Routes = [
    {
        path: 'call-detail-record/:id/delete',
        component: CallDetailRecordDeletePopupComponent,
        resolve: {
            callDetailRecord: CallDetailRecordResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callDetailRecord.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
