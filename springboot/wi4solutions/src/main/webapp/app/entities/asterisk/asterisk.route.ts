import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Asterisk } from 'app/shared/model/asterisk.model';
import { AsteriskService } from './asterisk.service';
import { AsteriskComponent } from './asterisk.component';
import { AsteriskDetailComponent } from './asterisk-detail.component';
import { AsteriskUpdateComponent } from './asterisk-update.component';
import { AsteriskDeletePopupComponent } from './asterisk-delete-dialog.component';
import { IAsterisk } from 'app/shared/model/asterisk.model';

@Injectable({ providedIn: 'root' })
export class AsteriskResolve implements Resolve<IAsterisk> {
    constructor(private service: AsteriskService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Asterisk> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Asterisk>) => response.ok),
                map((asterisk: HttpResponse<Asterisk>) => asterisk.body)
            );
        }
        return of(new Asterisk());
    }
}

export const asteriskRoute: Routes = [
    {
        path: 'asterisk',
        component: AsteriskComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'send-calls',
        component: AsteriskComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asterisk/:id/view',
        component: AsteriskDetailComponent,
        resolve: {
            asterisk: AsteriskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asterisk/new',
        component: AsteriskUpdateComponent,
        resolve: {
            asterisk: AsteriskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asterisk/:id/edit',
        component: AsteriskUpdateComponent,
        resolve: {
            asterisk: AsteriskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const asteriskPopupRoute: Routes = [
    {
        path: 'asterisk/:id/delete',
        component: AsteriskDeletePopupComponent,
        resolve: {
            asterisk: AsteriskResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.asterisk.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
