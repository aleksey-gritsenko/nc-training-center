import {TestBed} from '@angular/core/testing';

import {NotificationsStorageService} from './notifications-storage.service';

describe('NotificationsStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotificationsStorageService = TestBed.get(NotificationsStorageService);
    expect(service).toBeTruthy();
  });
});
