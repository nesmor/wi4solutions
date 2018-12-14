/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CallDetailRecordService } from 'app/entities/call-detail-record/call-detail-record.service';
import { ICallDetailRecord, CallDetailRecord } from 'app/shared/model/call-detail-record.model';

describe('Service Tests', () => {
    describe('CallDetailRecord Service', () => {
        let injector: TestBed;
        let service: CallDetailRecordService;
        let httpMock: HttpTestingController;
        let elemDefault: ICallDetailRecord;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(CallDetailRecordService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new CallDetailRecord(
                0,
                currentDate,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        calldate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a CallDetailRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        calldate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        calldate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new CallDetailRecord(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a CallDetailRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        calldate: currentDate.format(DATE_TIME_FORMAT),
                        clid: 'BBBBBB',
                        src: 'BBBBBB',
                        dst: 'BBBBBB',
                        dcontext: 'BBBBBB',
                        channel: 'BBBBBB',
                        dstchannel: 'BBBBBB',
                        lastapp: 'BBBBBB',
                        lastdata: 'BBBBBB',
                        duration: 1,
                        billsec: 1,
                        disposition: 'BBBBBB',
                        amaflags: 1,
                        accountcode: 'BBBBBB',
                        uniqueid: 'BBBBBB',
                        userfield: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        calldate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of CallDetailRecord', async () => {
                const returnedFromService = Object.assign(
                    {
                        calldate: currentDate.format(DATE_TIME_FORMAT),
                        clid: 'BBBBBB',
                        src: 'BBBBBB',
                        dst: 'BBBBBB',
                        dcontext: 'BBBBBB',
                        channel: 'BBBBBB',
                        dstchannel: 'BBBBBB',
                        lastapp: 'BBBBBB',
                        lastdata: 'BBBBBB',
                        duration: 1,
                        billsec: 1,
                        disposition: 'BBBBBB',
                        amaflags: 1,
                        accountcode: 'BBBBBB',
                        uniqueid: 'BBBBBB',
                        userfield: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        calldate: currentDate
                    },
                    returnedFromService
                );
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

            it('should delete a CallDetailRecord', async () => {
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
