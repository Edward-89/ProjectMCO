package com.diplom.project.resource;

public interface ForHelpText {
	
//	final static String ITEM_1 = new String(""
//			+ "1. Запускаємо програму МСО та формуємо множину допустимих\n"
//			+ "    проектних рішень у вигляді таблиці. Обираємо кількість\n"
//			+ "    варіантів від 1 до 100 за допомогою градуйованої шкали\n"
//			+ "    (або вказавши число у комірці справа від шкали).\n"
//			+ "    Натискаємо кнопку Create table.\n"); 

	
	
	final static String ITEM_1 = new String(""
			+ "1. Запускаємо програму МСО та формуємо множину допустимих"
			+ "проектних рішень у вигляді таблиці. Обираємо кількість"
			+ "варіантів від 1 до 100 за допомогою градуйованої шкали"
			+ "(або вказавши число у комірці справа від шкали)."
			+ "Натискаємо кнопку Create table.\n");   
	
	final static String ITEM_2 = new String(""
			+ ""
			+ ""
			+ ""
			+ ""
			+ ""
			+ "");
	
	final static String ITEM_3 = new String(""
			+ ""
			+ ""
			+ ""
			+ ""
			+ ""
			+ "");
	
	final static String ITEM_4 = new String(""
			+ ""
			+ ""
			+ ""
			+ ""
			+ ""
			+ "");
	
	final static String ITEM_5 = new String(""
			+ ""
			+ ""
			+ ""
			+ ""
			+ ""
			+ "");

	final static String ITEM_6 = new String(""
			+ ""
			+ ""
			+ ""
			+ ""
			+ ""
			+ "");
	
	/*
	 * MCO (Multiple Criteria Optimization)

1.	Запускаємо програму МСО та формуємо множину допустимих проектних рішень у вигляді таблиці.
 	Обираємо кількість варіантів від 1 до 100 за допомогою градуйованої шкали (або вказавши число у комірці справа від шкали).
 	Натискаємо кнопку Create table.
2.	Заповнюємо дані таблиці, задаємо показники якості проектних рішень.
    Ставимо відмітки Enabled для кожного критерію Кн, який буде приймати участь в подальших обчисленнях.
    Після проставлення відміток обираємо мінімізуємо чи максимізуємо обраний критерій, відмічаючи min або  max.
    Перевіряємо правильність введених даних і обраних параметрів,
    натискаємо почергово Load (фіксуємо дані) та Run (виконання нормалізації 
    та  знаходження Парето-оптимальних рішень).
3.	Виконуємо звуження підмножини Парето до єдиного рішення. 
	3.1	Лексикографічний метод – натискаємо кнопку  Priority, задаємо важливість кожного критерію,
	проставляючи відмітки  (від 1 до n, n- кількість критеріїв). Фіксуємо відмітки кнопкою Load і натискаємо  Run. 
	Отримуємо результат.
	3.2	Метод на основі функції цінності – натискаємо кнопку  Persent , задаємо функцію цінності
	у процентах для кожного критерію. Фіксуємо відмітки кнопкою Load і натискаємо  Run.
	Отримуємо результат.
Кнопка Reset слугує для закриття поточної таблиці і можливості створення нової множини допустимих проектних рішень.
File – Save  - збереження множини допустимих проектних рішень (при збереженні слід вказувати розширення файлу .mtm ; mtm – mathTable Model)
File – Open  - відкриття  раніше збереженої множини допустимих проектних рішень.


	 */
	
	final static String ALL_TEXT_HELP = new String(""
			+ "\t МСО (Multiple Criteria Optimization) \n\n"
			+ "1.  Запускаємо програму МСО та формуємо множину допустимих проектних рішень у вигляді таблиці. "
			+ "Обираємо кількість варіантів від 1 до 100 за допомогою градуйованої шкали поля Variants (або вказавши число у комірці справа від шкали). "
			+ "Натискаємо кнопку Create table. \n\n"
			+ "2.  Заповнюємо дані таблиці, задаємо показники якості варіантів систем. "
			+ "В полі Quality Indices ставимо відмітки Enabled для кожного показника  Кн, який буде приймати участь в подальших обчисленнях. Після проставлення відміток обираємо мінімізуємо чи максимізуємо обраний критерій, відмічаючи min або  max. Перевіряємо правильність введених даних, натискаємо почергово Load (фіксуємо дані) та Normalize (виконання нормалізації). "
			+"\n\n"
			+ "3.  Натискаємо Run для знаходження Парето-оптимальних варіантів. \n\n"
			+ "4.  Виконуємо звуження підмножини Парето до єдиного рішення. \n"
			+ "    4.1 Лексикографічний метод – натискаємо кнопку  Priority, задаємо важливість кожного критерію, "
			+ "проставляючи відмітки  (від 1 до n, n- кількість критеріїв). Фіксуємо відмітки кнопкою Load і натискаємо  Run. "
			+ "Отримуємо результат.\n"
			+ "    4.2 Метод на основі функції цінності – натискаємо кнопку  Persent , задаємо функцію цінності "
			+ "у процентах для кожного критерію. Фіксуємо відмітки кнопкою Load і натискаємо  Run. "
			+ "Отримуємо результат.\n\n"
			+ "Кнопка Reset слугує для закриття поточної таблиці і можливості створення нової множини допустимих проектних рішень.\n"
			+ "File – Save  - збереження множини допустимих проектних рішень (при збереженні слід вказувати розширення файлу .mtm ; mtm – mathTable Model) \n"
			+ "File – Open  - відкриття  раніше збереженої множини допустимих проектних рішень.\n"
						
			);
	
	
	
	
}
