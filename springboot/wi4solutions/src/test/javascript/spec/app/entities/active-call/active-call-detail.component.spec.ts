/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Wi4SolutionsTestModule } from '../../../test.module';
import { ActiveCallDetailComponent } from 'app/entities/active-call/active-call-detail.component';
import { ActiveCall } from 'app/shared/model/active-call.model';

describe('Component Tests', () => {
    describe('ActiveCall Management Detail Component', () => {
        let comp: ActiveCallDetailComponent;
        let fixture: ComponentFixture<ActiveCallDetailComponent>;
        const route = ({ data: of({ activeCall: new ActiveCall(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Wi4SolutionsTestModule],
                declarations: [ActiveCallDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ActiveCallDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActiveCallDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.activeCall).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
