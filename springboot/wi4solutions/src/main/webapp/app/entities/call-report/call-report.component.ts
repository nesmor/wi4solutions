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
    templateUrl: './call-report.component.html'
})
export class CallReportComponent implements OnInit, OnDestroy {
    callReports: ICallReport[];
    currentAccount: any;
    eventSubscriber: Subscription;
    fromDate: string;
    toDate: string;
    reportType: string;

    constructor(
        private callReportService: CallReportService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private datePipe: DatePipe,
        private router: Router
    ) {}

    loadAll() {
        if ((this.fromDate == null || this.fromDate == '') && (this.toDate == null || this.toDate)) {
            this.today();
        }
        console.log('*******Path called:' + this.router.url);
        if (this.router.url == '/call-report/by-type/week') {
            this.reportType = 'week';
            this.callFindByType('week');
        } else if (this.router.url == '/call-report/by-type/monthy') {
            this.reportType = 'monthy';
            this.callFindByType('monthy');
        } else if (this.router.url.indexOf('/call-report/by-period') >= 0) {
            const urlSegment = this.router.url.split('/');
            this.reportType = 'date';
            this.callFindByPeriod(urlSegment[3]);
        } else {
            this.reportType = 'date';
            this.callFindByDate();
        }
    }

    callFindByDate() {
        this.callReportService
            .findByDate({
                fromDate: this.fromDate,
                toDate: this.toDate
            })
            .subscribe(
                (res: HttpResponse<ICallReport[]>) => {
                    this.callReports = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    callFindByType(type: string) {
        this.callReportService
            .findByType(type, {
                fromDate: this.fromDate,
                toDate: this.toDate
            })
            .subscribe(
                (res: HttpResponse<ICallReport[]>) => {
                    this.callReports = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    callFindByPeriod(type: string) {
        this.callReportService.findByPeriod(type).subscribe(
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

    transition() {
        console.log('*******Ejecuting transition');
        this.loadAll();
    }

    registerChangeInCallReports() {
        this.eventSubscriber = this.eventManager.subscribe('callReportListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    previousMonth() {
        const dateFormat = 'yyyy-MM-dd';
        let fromDate: Date = new Date();

        if (fromDate.getMonth() === 0) {
            fromDate = new Date(fromDate.getFullYear() - 1, 11, fromDate.getDate());
        } else {
            fromDate = new Date(fromDate.getFullYear(), fromDate.getMonth() - 1, fromDate.getDate());
        }

        this.fromDate = this.datePipe.transform(fromDate, dateFormat);
    }

    today() {
        const dateFormat = 'yyyy-MM-dd';
        // Today + 1 day - needed if the current day must be included
        const today: Date = new Date();
        today.setDate(today.getDate() + 1);
        const date = new Date(today.getFullYear(), today.getMonth(), today.getDate());
        this.fromDate = this.datePipe.transform(date, dateFormat);
        this.toDate = this.datePipe.transform(date, dateFormat);
    }
}
