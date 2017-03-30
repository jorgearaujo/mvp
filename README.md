# mvp

Simple library to make easier the known MVP pattern for Android. This reduces the repetitive code along the classes.

#### 1. Add the project LightInject.

This library depends on the project LightInject, so you need to add it. Please check [here](https://github.com/jorgearaujo/lightinject).

#### 2. Create your ViewHolder

For this, you need to make the ViewHolder extend from BaseViewHolder

```java
public class MainViewHolder implements BaseViewHolder {

	public TextView helloWorldText;

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void generateViews(View view) {
		helloWorldText = (TextView) view.findViewById(R.id.hello_world);
	}
}
```

#### 3. Create your Fragment

It needs to implement an interface that extends from IBaseView, and the fragment needs to extend from BaseFragment. BaseFragment accepts two generic type arguments: the first one is the ViewHolder and the second one is the interface of the Presenter. Implement the methods of the interface.

You can access the view holder by using `getViewHolder`.

```java
public interface IMainView extends IBaseView {
    void setIpText(String text);
}
```

```java
public class MainFragment extends BaseFragment<MainViewHolder, IMainPresenter> implements IMainView {

  public void setHelloWorldText(String text) {
		  getViewHolder().helloWorldText.setText(text);
	}

}
```

#### 4. Create your Presenter

It needs to implement an interface that extends from IBasePresenter, and the presenter needs to extend from BasePresenter. BasePresenter accepts one generic type argument, which is the interface of the View. Implement the methods of the interface.

The lifecycle methods are called automatically, without the need of call them from the View. To access the methods of the view just use `getView()`.

```java
public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    @Override
    public void onResume() {
        getView().setHelloWorldText("hello!");
    }
}
```

#### 5. Add the mappings to the Binding Configuration

To make this work, you need to specify which Views and Presenters will implement the interfaces. For that, you need to specify that in the Binding Configuration of LightInject.

```java
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		new BindingConfiguration() {
			@Override
			public void configure() {
				from(IMainView.class).to(MainFragment.class).add();
				from(IMainPresenter.class).to(MainPresenter.class).add();
			}
		}.configure();
	}
}
```

#### 6. Example
You have available an example in the [following link](https://github.com/jorgearaujo/mvp-example).
