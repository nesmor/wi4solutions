/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { SipPeerService } from 'app/entities/sip-peer/sip-peer.service';
import { ISipPeer, SipPeer } from 'app/shared/model/sip-peer.model';

describe('Service Tests', () => {
    describe('SipPeer Service', () => {
        let injector: TestBed;
        let service: SipPeerService;
        let httpMock: HttpTestingController;
        let elemDefault: ISipPeer;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SipPeerService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new SipPeer(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0
            );
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

            it('should create a SipPeer', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new SipPeer(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SipPeer', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        host: 'BBBBBB',
                        nat: 'BBBBBB',
                        type: 'BBBBBB',
                        accountcode: 'BBBBBB',
                        amaflags: 'BBBBBB',
                        calllimit: 1,
                        callgroup: 'BBBBBB',
                        callerid: 'BBBBBB',
                        cancallforward: 'BBBBBB',
                        canreinvite: 'BBBBBB',
                        context: 'BBBBBB',
                        defaultip: 'BBBBBB',
                        dtmfmode: 'BBBBBB',
                        fromuser: 'BBBBBB',
                        fromdomain: 'BBBBBB',
                        insecure: 'BBBBBB',
                        language: 'BBBBBB',
                        mailbox: 'BBBBBB',
                        md5secret: 'BBBBBB',
                        deny: 'BBBBBB',
                        permit: 'BBBBBB',
                        mask: 'BBBBBB',
                        musiconhold: 'BBBBBB',
                        pickupgroup: 'BBBBBB',
                        qualify: 'BBBBBB',
                        regexten: 'BBBBBB',
                        restrictcid: 'BBBBBB',
                        rtptimeout: 'BBBBBB',
                        rtpholdtimeout: 'BBBBBB',
                        secret: 'BBBBBB',
                        setvar: 'BBBBBB',
                        disallow: 'BBBBBB',
                        allow: 'BBBBBB',
                        fullcontact: 'BBBBBB',
                        ipaddr: 'BBBBBB',
                        port: 1,
                        regserver: 'BBBBBB',
                        regseconds: 1,
                        lastms: 1,
                        username: 'BBBBBB',
                        defaultuser: 'BBBBBB',
                        subscribecontext: 'BBBBBB',
                        useragent: 'BBBBBB',
                        status: 1
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

            it('should return a list of SipPeer', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        host: 'BBBBBB',
                        nat: 'BBBBBB',
                        type: 'BBBBBB',
                        accountcode: 'BBBBBB',
                        amaflags: 'BBBBBB',
                        calllimit: 1,
                        callgroup: 'BBBBBB',
                        callerid: 'BBBBBB',
                        cancallforward: 'BBBBBB',
                        canreinvite: 'BBBBBB',
                        context: 'BBBBBB',
                        defaultip: 'BBBBBB',
                        dtmfmode: 'BBBBBB',
                        fromuser: 'BBBBBB',
                        fromdomain: 'BBBBBB',
                        insecure: 'BBBBBB',
                        language: 'BBBBBB',
                        mailbox: 'BBBBBB',
                        md5secret: 'BBBBBB',
                        deny: 'BBBBBB',
                        permit: 'BBBBBB',
                        mask: 'BBBBBB',
                        musiconhold: 'BBBBBB',
                        pickupgroup: 'BBBBBB',
                        qualify: 'BBBBBB',
                        regexten: 'BBBBBB',
                        restrictcid: 'BBBBBB',
                        rtptimeout: 'BBBBBB',
                        rtpholdtimeout: 'BBBBBB',
                        secret: 'BBBBBB',
                        setvar: 'BBBBBB',
                        disallow: 'BBBBBB',
                        allow: 'BBBBBB',
                        fullcontact: 'BBBBBB',
                        ipaddr: 'BBBBBB',
                        port: 1,
                        regserver: 'BBBBBB',
                        regseconds: 1,
                        lastms: 1,
                        username: 'BBBBBB',
                        defaultuser: 'BBBBBB',
                        subscribecontext: 'BBBBBB',
                        useragent: 'BBBBBB',
                        status: 1
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

            it('should delete a SipPeer', async () => {
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
