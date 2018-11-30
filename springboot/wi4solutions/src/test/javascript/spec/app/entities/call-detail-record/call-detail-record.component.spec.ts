/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallDetailRecordComponent } from 'app/entities/call-detail-record/call-detail-record.component';
import { CallDetailRecordService } from 'app/entities/call-detail-record/call-detail-record.service';
import { CallDetailRecord } from 'app/shared/model/call-detail-record.model';

describe('Component Tests', () => {
    describe('CallDetailRecord Management Component', () => {
        let comp: CallDetailRecordComponent;
        let fixture: ComponentFixture<CallDetailRecordComponent>;
        let service: CallDetailRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallDetailRecordComponent],
                providers: []
            })
                .overrideTemplate(CallDetailRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CallDetailRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CallDetailRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CallDetailRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.callDetailRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
