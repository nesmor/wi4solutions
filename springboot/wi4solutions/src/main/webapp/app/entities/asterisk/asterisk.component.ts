import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { JhiMetricsService } from 'app/admin/metrics/metrics.service';
import { Asterisk, IAsterisk } from 'app/shared/model/asterisk.model';
import { Principal } from 'app/core';
import { AsteriskService } from './asterisk.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { setInterval, clearInterval } from 'timers';
import { IMessage } from 'app/shared/model/message.model';

@Component({
    selector: 'jhi-asterisk',
    templateUrl: './asterisk.component.html'
})
export class AsteriskComponent implements OnInit, OnDestroy {
    asterisks: IAsterisk[];
    currentAccount: any;
    eventSubscriber: Subscription;
    phoneNumber: string;
    counter = 0;
    progressInterval: any;
    asterisk: IAsterisk;

    constructor(
        private asteriskService: AsteriskService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private metricsService: JhiMetricsService
    ) {}

    loadAll() {
        this.asteriskService.query().subscribe(
            (res: HttpResponse<IAsterisk[]>) => {
                this.asterisks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        /*  this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAsterisks();*/
    }

    ngOnDestroy() {
        //   this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAsterisk) {
        return item.id;
    }

    reloadAsterisk() {
        this.metricsService.reloadAsterisk().subscribe(data => {}, (res: HttpErrorResponse) => this.onError(res.message));
        this.refresh();
    }

    restartAsterisk() {
        this.metricsService.restartAsterisk().subscribe(data => {}, (res: HttpErrorResponse) => this.onError(res.message));
        this.refresh();
    }

    refresh() {
        this.progressInterval = setInterval(() => {
            this.counter = this.counter + 10;
            if (this.counter >= 100) {
                clearInterval(this.progressInterval);
                this.counter = 0;
            }
        }, 200);
    }

    sendTest() {
        var asterisk: Asterisk = { phoneNumber: this.phoneNumber };
        //  asterisk.setNumber = this.phoneNumber;
        this.asteriskService
            .sendCall(asterisk)
            .subscribe((res: HttpResponse<IMessage>) => this.processResponse(res), (res: HttpErrorResponse) => this.onError(res.message));
        this.refresh();
    }

    registerChangeInAsterisks() {
        //  this.eventSubscriber = this.eventManager.subscribe('asteriskListModification', response => this.loadAll());
    }

    private processResponse(res: HttpResponse<IMessage>) {
        console.log('**data:' + res.body.code);
        console.log('**message:' + res.body.message);
        if (res.body.code == 0) {
            this.onSuccess(res.body.message);
        } else {
            this.onError(res.body.message);
        }
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
    private onSuccess(successMessage: string) {
        this.jhiAlertService.success(successMessage, null, null);
    }
}
