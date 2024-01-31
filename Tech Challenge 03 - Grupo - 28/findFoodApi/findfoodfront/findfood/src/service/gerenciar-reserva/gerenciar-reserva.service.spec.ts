import { TestBed } from '@angular/core/testing';

import { GerenciarReservaService } from './gerenciar-reserva.service';

describe('GerenciarReservaService', () => {
  let service: GerenciarReservaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GerenciarReservaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
