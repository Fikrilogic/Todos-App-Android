package com.fikrisandi.framework.extension

import kotlinx.datetime.LocalDateTime


fun LocalDateTime.toDaysWithMontAndYear() = "${this.dayOfMonth}/${if(this.monthNumber < 10) "0" + this.monthNumber else this.monthNumber}/${this.year}"
fun LocalDateTime.toHoursAndMinute() = "${if(this.time.hour < 10) "0" + this.time.hour else this.time.hour}:${if(this.time.minute < 10) "0" + this.time.minute else this.time.minute}"