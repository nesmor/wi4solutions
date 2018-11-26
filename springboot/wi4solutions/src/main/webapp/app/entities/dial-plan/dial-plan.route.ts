import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DialPlan } from 'app/shared/model/dial-plan.model';
import { DialPlanService } from './dial-plan.service';
import { DialPlanComponent } from './dial-plan.component';
import { DialPlanDetailComponent } from './dial-plan-detail.component';
import { DialPlanUpdateComponent } from './dial-plan-update.component';
import { DialPlanDeletePopupComponent } from './dial-plan-delete-dialog.component';
import { IDialPlan } from 'app/shared/model/dial-plan.model';

@Injectable({ providedIn: 'root' })
export class DialPlanResolve implements Resolve<IDialPlan> {
    constructor(private service: DialPlanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DialPlan> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DialPlan>) => response.ok),
                map((dialPlan: HttpResponse<DialPlan>) => dialPlan.body)
            );
        }
        return of(new DialPlan());
    }
}

export const dialPlanRoute: Routes = [
    {
        path: 'dial-plan',
        component: DialPlanComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.dialPlan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dial-plan/:id/view',
        component: DialPlanDetailComponent,
        resolve: {
            dialPlan: DialPlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.dialPlan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dial-plan/new',
        component: DialPlanUpdateComponent,
        resolve: {
            dialPlan: DialPlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.dialPlan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dial-plan/:id/edit',
        component: DialPlanUpdateComponent,
        resolve: {
            dialPlan: DialPlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.dialPlan.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dialPlanPopupRoute: Routes = [
    {
        path: 'dial-plan/:id/delete',
        component: DialPlanDeletePopupComponent,
        resolve: {
            dialPlan: DialPlanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.dialPlan.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
