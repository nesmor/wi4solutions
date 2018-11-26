import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SipPeer } from 'app/shared/model/sip-peer.model';
import { SipPeerService } from './sip-peer.service';
import { SipPeerComponent } from './sip-peer.component';
import { SipPeerDetailComponent } from './sip-peer-detail.component';
import { SipPeerUpdateComponent } from './sip-peer-update.component';
import { SipPeerDeletePopupComponent } from './sip-peer-delete-dialog.component';
import { ISipPeer } from 'app/shared/model/sip-peer.model';

@Injectable({ providedIn: 'root' })
export class SipPeerResolve implements Resolve<ISipPeer> {
    constructor(private service: SipPeerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SipPeer> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SipPeer>) => response.ok),
                map((sipPeer: HttpResponse<SipPeer>) => sipPeer.body)
            );
        }
        return of(new SipPeer());
    }
}

export const sipPeerRoute: Routes = [
    {
        path: 'sip-peer',
        component: SipPeerComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.sipPeer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sip-peer/:id/view',
        component: SipPeerDetailComponent,
        resolve: {
            sipPeer: SipPeerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.sipPeer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sip-peer/new',
        component: SipPeerUpdateComponent,
        resolve: {
            sipPeer: SipPeerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.sipPeer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sip-peer/:id/edit',
        component: SipPeerUpdateComponent,
        resolve: {
            sipPeer: SipPeerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.sipPeer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sipPeerPopupRoute: Routes = [
    {
        path: 'sip-peer/:id/delete',
        component: SipPeerDeletePopupComponent,
        resolve: {
            sipPeer: SipPeerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'wi4SolutionsApp.sipPeer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
