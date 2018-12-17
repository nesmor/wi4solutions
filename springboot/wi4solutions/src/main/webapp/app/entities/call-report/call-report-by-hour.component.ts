import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ActivatedRoute, Router } from '@angular/router';
import { ICallReport } from 'app/shared/model/call-report.model';
import { Principal } from 'app/core';
import { CallReportService } from './call-report.service';
import { DatePipe } from '@angular/common';

@Component({
    selector: 'jhi-call-report',
    templateUrl: './call-report-by-hour.component.html'
})
export class CallReportByHourComponent implements OnInit, OnDestroy {
    callReports: ICallReport[];
    currentAccount: any;
    eventSubscriber: Subscription;
    fromDate: string;
    dateFormat = 'yyyy-MM-dd';

    constructor(
        private callReportService: CallReportService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private datePipe: DatePipe,
        private router: Router
    ) {}

    loadAll() {
        if (this.fromDate == null || this.fromDate == '') {
            const date: Date = new Date();
            date.setUTCFullYear(2018, 3, 31);
            this.fromDate = this.datePipe.transform(date, this.dateFormat);
        }
        this.callReportService.findByHour({ fromDate: this.fromDate }).subscribe(
            (res: HttpResponse<ICallReport[]>) => {
                this.callReports = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCallReports();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICallReport) {
        return item.id;
    }

    registerChangeInCallReports() {
        this.eventSubscriber = this.eventManager.subscribe('callReportListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    today() {
        // Today + 1 day - needed if the current day must be included
        const today: Date = new Date();
        today.setDate(today.getDate() + 1);
        const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());
        this.fromDate = this.datePipe.transform(date, this.dateFormat);
    }

    transition() {
        this.loadAll();
    }
}
