package com.azh.dto;

import java.math.BigDecimal;

public class CarAnnotationDto {
	private int speed;

	private BigDecimal price;

	public CarAnnotationDto() {
	}

	public CarAnnotationDto(int speed, BigDecimal price) {
		this.speed = speed;
		this.price = price;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
