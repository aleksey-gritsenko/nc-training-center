import {TestBed} from '@angular/core/testing';

import {ManagingService} from './managing.service';

describe('ManagingService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: ManagingService = TestBed.get(ManagingService);
        expect(service).toBeTruthy();
    });
});
