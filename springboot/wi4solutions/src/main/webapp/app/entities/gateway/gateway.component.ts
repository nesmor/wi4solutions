import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGateway } from 'app/shared/model/gateway.model';
import { Principal } from 'app/core';
import { GatewayService } from './gateway.service';

@Component({
    selector: 'jhi-gateway',
    templateUrl: './gateway.component.html'
})
export class GatewayComponent implements OnInit, OnDestroy {
    gateways: IGateway[];
    currentAccount: any;
    eventSubscriber: Subscription;
    error: any;
    success: any;

    constructor(
        private gatewayService: GatewayService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.gatewayService
            .query({
                peerType: 'GATEWAY'
            })
            .subscribe(
                (res: HttpResponse<IGateway[]>) => {
                    this.gateways = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGateways();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    setActive(gateway, isActivated) {
        gateway.status = isActivated;

        this.gatewayService.update(gateway).subscribe(response => {
            if (response.status === 200) {
                this.error = null;
                this.success = 'OK';
                this.loadAll();
            } else {
                this.success = null;
                this.error = 'ERROR';
            }
        });
    }

    trackId(index: number, item: IGateway) {
        return item.id;
    }

    registerChangeInGateways() {
        this.eventSubscriber = this.eventManager.subscribe('gatewayListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
