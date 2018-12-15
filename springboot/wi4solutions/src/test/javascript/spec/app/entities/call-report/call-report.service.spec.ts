/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import * as moment from 'moment';
import { take, map } from 'rxjs/operators';
import { CallReportService } from 'app/entities/call-report/call-report.service';
import { ICallReport, CallReport } from 'app/shared/model/call-report.model';

describe('Service Tests', () => {
    describe('CallReport Service', () => {
        let injector: TestBed;
        let service: CallReportService;
        let httpMock: HttpTestingController;
        let currentDate: moment.Moment;
        let elemDefault: ICallReport;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CallReportService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new CallReport(0, currentDate, currentDate, 0, 0, 0, 0, 0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CallReport', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new CallReport(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CallReport', async () => {
                currentDate = moment();
                const returnedFromService = Object.assign(
                    {
                        fromDate: currentDate,
                        toDate: currentDate,
                        failedCalls: 1,
                        totalCalls: 1,
                        totalDuration: 1,
                        asr: 1,
                        acd: 1,
                        minutes: 1,
                        connectedCalls: 1,
                        reportType: 'BBBBBB',
                        hour: 1,
                        date: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CallReport', async () => {
                currentDate = moment();
                const returnedFromService = Object.assign(
                    {
                        fromDate: currentDate,
                        toDate: currentDate,
                        failedCalls: 1,
                        totalCalls: 1,
                        totalDuration: 1,
                        asr: 1,
                        acd: 1,
                        minutes: 1,
                        connectedCalls: 1,
                        reportType: 'BBBBBB',
                        hour: 1,
                        date: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a CallReport', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
