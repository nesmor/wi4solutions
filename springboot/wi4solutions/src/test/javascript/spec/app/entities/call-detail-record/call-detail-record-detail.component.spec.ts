/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { CallDetailRecordDetailComponent } from 'app/entities/call-detail-record/call-detail-record-detail.component';
import { CallDetailRecord } from 'app/shared/model/call-detail-record.model';

describe('Component Tests', () => {
    describe('CallDetailRecord Management Detail Component', () => {
        let comp: CallDetailRecordDetailComponent;
        let fixture: ComponentFixture<CallDetailRecordDetailComponent>;
        const route = ({ data: of({ callDetailRecord: new CallDetailRecord(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [CallDetailRecordDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CallDetailRecordDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CallDetailRecordDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.callDetailRecord).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
