import { TestBed } from '@angular/core/testing';

import { RecoverService } from './recover.service';

describe('RecoverService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecoverService = TestBed.get(RecoverService);
    expect(service).toBeTruthy();
  });
});
