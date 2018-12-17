import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ActiveCall } from 'app/shared/model/active-call.model';
import { ActiveCallService } from './active-call.service';
import { ActiveCallComponent } from './active-call.component';
import { ActiveCallDetailComponent } from './active-call-detail.component';
import { ActiveCallUpdateComponent } from './active-call-update.component';
import { ActiveCallDeletePopupComponent } from './active-call-delete-dialog.component';
import { IActiveCall } from 'app/shared/model/active-call.model';

@Injectable({ providedIn: 'root' })
export class ActiveCallResolve implements Resolve<IActiveCall> {
    constructor(private service: ActiveCallService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ActiveCall> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ActiveCall>) => response.ok),
                map((activeCall: HttpResponse<ActiveCall>) => activeCall.body)
            );
        }
        return of(new ActiveCall());
    }
}

export const activeCallRoute: Routes = [
    {
        path: 'active-call',
        component: ActiveCallComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.activeCall.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'active-call/:id/view',
        component: ActiveCallDetailComponent,
        resolve: {
            activeCall: ActiveCallResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.activeCall.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'active-call/new',
        component: ActiveCallUpdateComponent,
        resolve: {
            activeCall: ActiveCallResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.activeCall.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'active-call/:id/edit',
        component: ActiveCallUpdateComponent,
        resolve: {
            activeCall: ActiveCallResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.activeCall.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const activeCallPopupRoute: Routes = [
    {
        path: 'active-call/:id/delete',
        component: ActiveCallDeletePopupComponent,
        resolve: {
            activeCall: ActiveCallResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.activeCall.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
