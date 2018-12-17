import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CallReport } from 'app/shared/model/call-report.model';
import { CallReportService } from './call-report.service';
import { CallReportComponent } from './call-report.component';
import { CallReportByHourComponent } from './call-report-by-hour.component';
import { CallReportDetailComponent } from './call-report-detail.component';
import { CallReportUpdateComponent } from './call-report-update.component';
import { CallReportDeletePopupComponent } from './call-report-delete-dialog.component';
import { ICallReport } from 'app/shared/model/call-report.model';

@Injectable({ providedIn: 'root' })
export class CallReportResolve implements Resolve<ICallReport> {
    constructor(private service: CallReportService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CallReport> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CallReport>) => response.ok),
                map((callReport: HttpResponse<CallReport>) => callReport.body)
            );
        }
        return of(new CallReport());
    }
}

export const callReportRoute: Routes = [
    {
        path: 'call-report',
        component: CallReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-report/by-date',
        component: CallReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-report/by-hour',
        component: CallReportByHourComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-report/by-type/:type',
        component: CallReportComponent,
        resolve: {
            callReport: CallReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },

    {
        path: 'call-report/:id/view',
        component: CallReportDetailComponent,
        resolve: {
            callReport: CallReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-report/new',
        component: CallReportUpdateComponent,
        resolve: {
            callReport: CallReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'call-report/:id/edit',
        component: CallReportUpdateComponent,
        resolve: {
            callReport: CallReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const callReportPopupRoute: Routes = [
    {
        path: 'call-report/:id/delete',
        component: CallReportDeletePopupComponent,
        resolve: {
            callReport: CallReportResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.callReport.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
