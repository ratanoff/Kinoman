package ru.ratanov.kinoman.presentation.presenter.search;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import ru.ratanov.kinoman.model.content.ISearchItem;
import ru.ratanov.kinoman.model.content.SearchItem;
import ru.ratanov.kinoman.model.search.ISearchAPI;
import ru.ratanov.kinoman.model.search.SearchAPI;
import ru.ratanov.kinoman.model.utils.QueryPreferences;
import ru.ratanov.kinoman.presentation.view.search.SearchView;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {
    public void doSearch(Context context, String query) {
        getViewState().showProgress();

        String searchType = QueryPreferences.getStoredQuery(context, "search_type");
        switch (searchType) {
            case "regular_search":
                SearchAPI searchAPI = new SearchAPI(this);
                searchAPI.search(query);
                break;
            case "i_search":
                ISearchAPI iSearchAPI = new ISearchAPI(this);
                iSearchAPI.search(query);
                break;
        }
    }

    public void updatePageI(List<ISearchItem> items) {
        getViewState().hideProgress();
        getViewState().updatePageI(items);
    }

    public void updatePage(List<SearchItem> items) {
        getViewState().hideProgress();
        getViewState().updatePage(items);
    }
}
