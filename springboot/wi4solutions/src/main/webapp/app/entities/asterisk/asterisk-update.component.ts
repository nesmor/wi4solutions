import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAsterisk } from 'app/shared/model/asterisk.model';
import { AsteriskService } from './asterisk.service';

@Component({
    selector: 'jhi-asterisk-update',
    templateUrl: './asterisk-update.component.html'
})
export class AsteriskUpdateComponent implements OnInit {
    asterisk: IAsterisk;
    isSaving: boolean;

    constructor(private asteriskService: AsteriskService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ asterisk }) => {
            this.asterisk = asterisk;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.asterisk.id !== undefined) {
            this.subscribeToSaveResponse(this.asteriskService.update(this.asterisk));
        } else {
            this.subscribeToSaveResponse(this.asteriskService.create(this.asterisk));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAsterisk>>) {
        result.subscribe((res: HttpResponse<IAsterisk>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
