import { TestBed } from '@angular/core/testing';

import { SpringAuthService } from './spring-auth.service';

describe('SpringAuthService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpringAuthService = TestBed.get(SpringAuthService);
    expect(service).toBeTruthy();
  });
});
