package com.mc.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instance) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instance);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataVencimento(calendar.getTime());
		
	}
}
