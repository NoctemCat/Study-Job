package com.example.study_job.data.test

import kotlin.random.Random

class ProffPair (
    var aProfession: String,
    var bProfession: String
){
    companion object{
        fun getProffPairList(): List<ProffPair>{
            return listOf(
                ProffPair("Инженер-технолог", "Инженер-конструктор"),
                ProffPair("Электрорадиотехник", "Врач-терапевт"),
                ProffPair("Оператор станков с числовым программным управлением",
                    "Кодировщик (обработка информации)"),
                ProffPair("Фотограф", "Коммерсант"),
                ProffPair("Спасатель МЧС", "Дизайнер"),
                ProffPair("Политолог", "Психиатр"),
                ProffPair("Ученый химик", "Бухгалтер"),
                ProffPair("Философ", "Частный предприниматель"),
                ProffPair("Лингвист", "Модельер"),
                ProffPair("Инспектор службы занятости населения",
                    "Статист"),
                ProffPair("Социальный педагог", "Биржевой маклер"),
                ProffPair("Тренер", "Искусствовед"),
                ProffPair("Нотариус", "Менеджер"),
                ProffPair("Перфораторщик", "Художник"),
                ProffPair("Лидер политической партии, общего движения",
                    "Писатель"),
                ProffPair("Закройщик", "Метеоролог"),
                ProffPair("Водитель", "Работник пресс-службы"),
                ProffPair("Чертежник", "Риэлтер"),
                ProffPair("Специалист по ремонту компьютеров и оргтехники",
                    "Секретарь-референт"),
                ProffPair("Микробиолог", "Психолог"),
                ProffPair("Видеооператор", "Режиссер"),
                ProffPair("Экономист", "Провизор"),
                ProffPair("Зоолог", "Главный инженер"),
                ProffPair("Программист", "Архитектор"),
                ProffPair("Работник инспекции по делам несовершеннолетних",
                    "Коммивояжер (сетевой маркетинг)"),
                ProffPair("Преподаватель", "Биржевой маклер"),
                ProffPair("Воспитатель", "Декоратор"),
                ProffPair("Реставратор", "Зав. отделом предприятия"),
                ProffPair("Корректор", "Литератор и кинокритик"),
                ProffPair("Фермер", "Визажист"),
                ProffPair("Экспедитор", "Редактор"),
                ProffPair("Ветеринар", "Директор (финансовый)"),
                ProffPair("Автомеханик", "Стилист"),
                ProffPair("Археолог", "Эксперт"),
                ProffPair("Библиограф", "Корреспондент"),
                ProffPair("Эколог", "Актер"),
                ProffPair("Логопед", "Контролер"),
                ProffPair("Адвокат", "Директор (глава АО)"),
                ProffPair("Кассир", "Продюсер"),
                ProffPair("Поэт, писатель ", "Продавец"),
                ProffPair("Криминалист (баллистик)", "Композитор"),
            )
        }
    }
}

//val btn = holder.button!!
//
//btn.text = "Рандом"
//btn.setOnClickListener{
//    val arr: Array<String>? = mViewModel.testResult.value
//
//    if (arr != null) {
//        for (index in 0..41) {
//            if(Random.nextInt(0, 2) == 0){
//                mViewModel.setResult(index, "A")
//            }else{
//                mViewModel.setResult(index, "B")
//            }
//
//        }
//    }
//}